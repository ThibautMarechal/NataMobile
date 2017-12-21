package be.helmo.natamobile.storage;
/**
import com.google.cloud.ReadChannel;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.*;
**/

public class GoogleStorage {
/**
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

    public byte[] getMedia(String onlinePath) throws IOException {
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

    public boolean deleteMedia(String onlinePath) {
        return storage.delete(BlobId.of(bucketName, onlinePath.toString()));
    }

    public boolean exist(String onlinePath) {
        try {
            return getMedia(onlinePath).length == 0;
        } catch (IOException ex) {
            return false;
        }
    }**/
}
