package run.halo.starter;

import lombok.Getter;

import java.io.File;
import java.util.function.Function;

/**
 * @description: WebpPluginConst
 * <p>
 * https://storage.googleapis.com/downloads.webmproject.org/releases/webp/index.html
 * @author: pan
 **/
public interface WebpPluginConst {

    String lib_path = "libwebp";
    String lib_version = "version.1.2.4";
    OS current_os = OS.getCurrentOs();

    static String buildCodeLibPath(String inputFile) {
        return lib_path + File.separator
                + lib_version + File.separator
                + current_os.getPath() + File.separator
                + "bin" + File.separator
                + current_os.getBin()
                + " "
                + inputFile
                + " -o "
                + (inputFile + ".webp");
    }

    enum OS {
        windows_x64("windows-x64", "cwebp.exe",
                (os) -> os.startsWith("win") || os.startsWith("Win"),
                (arch) -> false
        ),
        windows_x64_no_wic("windows-x64-no-wic", "cwebp.exe",
                (os) -> false,
                (arch) -> false
        ),
        mac_x86_64("mac-x86-64", "cwebp",
                (os) -> false,
                (arch) -> false
        ),
        mac_arm64("mac-arm64", "cwebp",
                (os) -> os.startsWith("Mac") || os.startsWith("mac"),
                (arch) -> arch.contains("aarch64")
        ),
        linux_x86_64("linux-x86-64", "cwebp",
                (os) -> false,
                (arch) -> false
        );
        @Getter
        private final String path;
        @Getter
        private final String bin;
        private final Function<String, Boolean> osFn;
        private final Function<String, Boolean> archFn;

        OS(String path, String bin, Function<String, Boolean> osFn, Function<String, Boolean> archFn) {
            this.path = path;
            this.bin = bin;
            this.osFn = osFn;
            this.archFn = archFn;
        }

        /**
         * 操作系统名称
         * <p>
         * macos: Mac OS X
         */
        public static final String name = System.getProperty("os.name");
        /**
         * cpu架构
         * <p>
         * aarch64
         */
        public static final String arch = System.getProperty("os.arch");

        public static OS getCurrentOs() {
            for (OS value : OS.values()) {
                if (value.osFn.apply(name) && value.archFn.apply(arch)) {
                    return value;
                }
            }
            throw new RuntimeException("暂不支持此操作系统");
        }
    }


}