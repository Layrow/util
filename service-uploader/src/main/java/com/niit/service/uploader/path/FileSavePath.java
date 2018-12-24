package com.niit.service.uploader.path;

public interface FileSavePath {
    String fullPath(String type);

    String relativePath();

    String  Path(String type);
}
