package com.javaidm;

import com.javaidm.modules.Fileinfo;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DownloadThread extends Thread{
    private Fileinfo file;
    private DownloadManeger maneger;
    public DownloadThread(Fileinfo file,DownloadManeger maneger){
        this.file=file;
        this.maneger=maneger;
    }

    @Override
    public void run(){
        this.file.setStatus("DOWNLOADING");
        this.maneger.updateUI(this.file);
        try {
            //Download Logic
            Files.copy(new URL(this.file.getUrl()).openStream(), Paths.get(this.file.getPath()));
            this.file.setStatus("DONE");
        } catch (IOException e) {
            this.file.setStatus("FAILD");
            System.out.println("DOWNLODING FAILD!!");
            e.printStackTrace();
        }
        this.maneger.updateUI(this.file);

    }
}
