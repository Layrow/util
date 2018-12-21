package com.niit.service.uploader;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class EnableUploaderImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata metadata) {
        String[] strings = {"com.niit.service.uploader.UploaderConfiguration"};
        return strings;
    }
}
