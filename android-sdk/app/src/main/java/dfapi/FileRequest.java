package dfapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

/**
 * Used for sending a file request to the server
 */
public class FileRequest {
    /* Identifier/Name for the file, localized to requested resource. */
    @JsonProperty("name")
    private String name = null;
    /* Full path of the file, from the service including container. */
    @JsonProperty("path")
    private String path = null;
    /* The media type of the content of the file. */
    @JsonProperty("content_type")
    private String content_type = null;
    /* An array of name-value pairs. */
    @JsonProperty("metadata")
    private List<String> metadata = new ArrayList<>();
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public String getContent_type() {
        return content_type;
    }
    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public List<String> getMetadata() {
        return metadata;
    }
    public void setMetadata(List<String> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString()  {
        return "class FileRequest {\n" +
                "  name: " + name + "\n" +
                "  path: " + path + "\n" +
                "  content_type: " + content_type + "\n" +
                "  metadata: " + metadata + "\n" +
                "}\n";
    }
}

