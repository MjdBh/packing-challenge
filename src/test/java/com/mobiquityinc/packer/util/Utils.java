package com.mobiquityinc.packer.util;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public final class Utils {

    public Utils() {
    }

    public static String getAbsolutePath(String classpathResource) {
        try {
            return Paths.get(ClassLoader.getSystemResource(classpathResource).toURI()).toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to find the file: " + classpathResource);
        }
    }
}
