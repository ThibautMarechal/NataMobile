package be.helmo.natamobile.storage;
/*
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

import be.helmo.natamobile.tools.HELMoCredentialsProvider;
*/

public class GoogleStorage {
/*
    private final Storage storage;
    private String bucketName;

    public GoogleStorage() {
        storage = StorageOptions.newBuilder()
                .setCredentials(HELMoCredentialsProvider.getCredential())
                .build()
                .getService();
        bucketName = "nat-test";
    }

    public void uploadPicture(String path, String onlinePath, String ext) throws IOException {
        if (isASubfolder(onlinePath)) uploadFolder(onlinePath);
        uploadMedia(path, onlinePath, "image/" + ext);
    }

    public void uploadVideoMP4(String path, String onlinePath) throws IOException {
        if (isASubfolder(onlinePath)) uploadFolder(onlinePath);
        uploadMedia(path, onlinePath, "video/mp4");
    }

    public void uploadAudioMP3(String path, String onlinePath) throws IOException {
        if (isASubfolder(onlinePath)) uploadFolder(onlinePath);
        uploadMedia(path, onlinePath, "audio/mpeg");
    }

    private void uploadMedia(String path, String onlinePath, String mediaType) throws IOException {
        BlobId blobId = BlobId.of(bucketName, onlinePath.toString().replace("\\", "/"));
        BlobInfo blobInfo = BlobInfo
                .newBuilder(blobId)
                .setContentType(mediaType)
                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                .build();

        uploadContent(path, blobInfo);
    }

    public void uploadFolder(String fullFolderName) {
        BlobId blobId = BlobId.of(bucketName, fullFolderName + "/");
        BlobInfo blobInfo = BlobInfo
                .newBuilder(blobId)
                .setContentType("Folder/folder")
                .build();
        storage.create(blobInfo, new byte[0]);
    }

    private void uploadContent(String uploadFrom, BlobInfo blobInfo) throws IOException {
        File data = new File(uploadFrom);
        if (data.length() > 1_000_000) {
            // When content is not available or large (1MB or more) it is recommended
            // to write it in chunks via the blob's channel writer.
            try (WriteChannel writer = storage.writer(blobInfo)) {
                byte[] buffer = new byte[1024];
//                try (InputStream input = Files.newInputStream(uploadFrom)) {
                try (InputStream input = new FileInputStream(data)) {
                    int limit;
                    while ((limit = input.read(buffer)) >= 0) {
                        writer.write(ByteBuffer.wrap(buffer, 0, limit));
                    }
                }
            }
        } else {
            byte[] bytes = convertFileToByteArray(data);
            // create the blob in one request.
            storage.create(blobInfo, bytes);
        }
    }

    public static byte[] convertFileToByteArray(File f)
    {
        byte[] byteArray = null;
        try
        {
            InputStream inputStream = new FileInputStream(f);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024*8];
            int bytesRead =0;

            while ((bytesRead = inputStream.read(b)) != -1)
            {
                bos.write(b, 0, bytesRead);
            }

            byteArray = bos.toByteArray();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return byteArray;
    }


    private boolean isASubfolder(String path) {
        return path.startsWith("\\");
    }

    public boolean deleteMedia(String onlinePath) {
        return storage.delete(BlobId.of(bucketName, onlinePath.toString()));
    }*/
}
