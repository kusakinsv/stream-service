package ru.one.stream.desktop.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BrowserLauncher {
    @Value("${server.startpage}")
    private String url;
    @Value("${server.launch-browser-on-start}")
    private String launchBrowser;

    public void start() {
        if (launchBrowser.equals("true")) launchBrowser();
    }

    @SneakyThrows
    private void launchBrowser() {
        String operationSystem = System.getProperty("os.name").toLowerCase();
        if (operationSystem.contains("win")) {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);;
        } else if (operationSystem.contains("mac")) {
            Runtime.getRuntime().exec("open " + url);
        } else if (operationSystem.contains("nix") || operationSystem.contains("nux")) {
            Runtime runtime = Runtime.getRuntime();
            List<String> browserList = Arrays.asList(
                    "firefox",
                    "chrome",
                    "opera",
                    "netscape",
                    "links",
                    "brave",
                    "mozilla"
            );
            StringBuffer cmd = new StringBuffer();
            for (int i = 0; i < browserList.size(); i++) {
                if (i == 0)
                    cmd.append(String.format("%s \"%s\"", browserList.get(i), url));
                else
                    cmd.append(String.format(" || %s \"%s\"", browserList.get(i), url));
            }
            runtime.exec(new String[]{"sh", "-c", cmd.toString()});
        }
    }
}

