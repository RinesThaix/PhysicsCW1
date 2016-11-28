package ru.luvas.physics.cw1;

import java.awt.EventQueue;

/**
 *
 * @author 0xC0deBabe <iam@kostya.sexy>
 */
public class Launcher {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new MainFrame());
    }
    
    private static String getImplVersion() {
        String version = Launcher.class.getPackage().getImplementationVersion();
        if(version == null)
            return "unknown";
        return version;
    }
    
    public static String getCommitId() {
        String version = getImplVersion();
        if(version.equals("unknown"))
            return version;
        return version.split("\\-")[0];
    }
    
    public static String getCommitDate() {
        String version = getImplVersion();
        if(version.equals("unknown"))
            return "";
        return version.split("\\-")[1];
    }
    
}
