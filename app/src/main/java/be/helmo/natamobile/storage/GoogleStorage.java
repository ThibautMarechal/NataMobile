package be.helmo.natamobile.storage;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.cloud.ReadChannel;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import be.helmo.natamobile.tools.HELMoCredentialsProvider;

public class GoogleStorage {

    private final Storage storage;
    private String bucketName;

    public GoogleStorage() {
        storage = StorageOptions.newBuilder()
                .setCredentials(HELMoCredentialsProvider.getCredential())
                .build()
                .getService();
        bucketName = "nat-test";
    }

    public void uploadPicture(Path path, Path onlinePath, String ext) throws IOException {
        if (isASubfolder(onlinePath)) uploadFolder(onlinePath);
        uploadMedia(path, onlinePath, "image/" + ext);
    }

    public void uploadVideoMP4(Path path, Path onlinePath) throws IOException {
        if (isASubfolder(onlinePath)) uploadFolder(onlinePath);
        uploadMedia(path, onlinePath, "video/mp4");
    }

    public void uploadAudioMP3(Path path, Path onlinePath) throws IOException {
        if (isASubfolder(onlinePath)) uploadFolder(onlinePath);
        uploadMedia(path, onlinePath, "audio/mpeg");
    }

    private void uploadMedia(Path path, Path onlinePath, String mediaType) throws IOException {
        BlobId blobId = BlobId.of(bucketName, onlinePath.toString().replace("\\", "/"));
        BlobInfo blobInfo = BlobInfo
                .newBuilder(blobId)
                .setContentType(mediaType)
                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                .build();

        uploadContent(path, blobInfo);
    }

    public void uploadFolder(Path fullFolderName) {
        BlobId blobId = BlobId.of(bucketName, fullFolderName + "/");
        BlobInfo blobInfo = BlobInfo
                .newBuilder(blobId)
                .setContentType("Folder/folder")
                .build();
        storage.create(blobInfo, new byte[0]);
    }

    private void uploadContent(Path uploadFrom, BlobInfo blobInfo) throws IOException {
        if (Files.size(uploadFrom) > 1_000_000) {
            // When content is not available or large (1MB or more) it is recommended
            // to write it in chunks via the blob's channel writer.
            try (WriteChannel writer = storage.writer(blobInfo)) {
                byte[] buffer = new byte[1024];
                try (InputStream input = Files.newInputStream(uploadFrom)) {
                    int limit;
                    while ((limit = input.read(buffer)) >= 0) {
                        writer.write(ByteBuffer.wrap(buffer, 0, limit));
                    }
                }
            }
        } else {
            byte[] bytes = Files.readAllBytes(uploadFrom);
            // create the blob in one request.
            storage.create(blobInfo, bytes);
        }
    }


    private boolean isASubfolder(Path path) {
        return path.startsWith("\\");
    }

    public byte[] getMedia(Path onlinePath) throws IOException {
        Blob blob = storage.get(BlobId.of(bucketName, onlinePath.toString().replace("\\", "/")));
        if (blob == null) {
            System.out.println("No such object");
            return new byte[0];
        }

        byte[] rtn;

        if (blob.getSize() < 1_000_000) {
            // Blob is small read all its content in one request
            return blob.getContent();
        } else {
            // When Blob size is big or unknown use the blob's channel reader.
            try (ReadChannel reader = blob.reader()) {
                List<Byte> content = new LinkedList<>();
                ByteBuffer bytes = ByteBuffer.allocate(64 * 1024);
                while (reader.read(bytes) > 0) {
                    bytes.flip();
                    for (byte tmp : bytes.array())
                        content.add(tmp);
                    bytes.clear();
                }
                rtn = new byte[content.size()];
                for (int i = 0; i < rtn.length; i++)
                    rtn[i] = content.get(i);
            }
        }
        return rtn;
    }

    public boolean deleteMedia(Path onlinePath) {
        return storage.delete(BlobId.of(bucketName, onlinePath.toString()));
    }

    public boolean exist(Path onlinePath) {
        try {
            return getMedia(onlinePath).length == 0;
        } catch (IOException ex) {
            return false;
        }
    }
}
