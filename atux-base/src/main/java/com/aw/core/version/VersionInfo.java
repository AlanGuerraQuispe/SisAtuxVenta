/**
 * User: Juan Carlos Vergara
 * Date: 11/02/2009
 * Time: 08:08:59 PM
 */
package com.aw.core.version;

public class VersionInfo {
    private static VersionInfo ourInstance = new VersionInfo();

    private String version = "Undefined";

    public static VersionInfo instance() {
        return ourInstance;
    }

    private VersionInfo() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
