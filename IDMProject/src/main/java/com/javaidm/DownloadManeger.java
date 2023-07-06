package com.javaidm;

import com.javaidm.config.AppConfig;
import com.javaidm.modules.Fileinfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.File;

public class DownloadManeger {

    @FXML
    private TableView<Fileinfo> TableView;

    @FXML
    private TextField urlfield;

    public int index=0;

    @FXML
    void ButtonClick(ActionEvent event) {
        String url=urlfield.getText().trim();
        String filename=url.substring(url.lastIndexOf("/")+1);
        String status="DOWNLOADING";
        String action="OPEN";
        String path= AppConfig.Path+ File.separator+filename;
        Fileinfo file=new Fileinfo((index+1)+"",filename,url,status,action,path);
        this.index=this.index+1;
        DownloadThread thread=new DownloadThread(file,this);
        this.TableView.getItems().add(Integer.parseInt(file.getIndex())-1,file);
        thread.start();
    }
    public void updateUI(Fileinfo metafile) {
        System.out.println(metafile);
        this.TableView.getItems().get(Integer.parseInt(metafile.getIndex())-1);
    }

    @FXML
    public void initialize(){
        TableColumn<Fileinfo,String>sn= (TableColumn<Fileinfo, String>) this.TableView.getColumns().get(0);
        sn.setCellValueFactory(p -> {
            return p.getValue().indexProperty();
        });
        TableColumn<Fileinfo,String>filename= (TableColumn<Fileinfo, String>) this.TableView.getColumns().get(1);
        filename.setCellValueFactory(p -> {
            return p.getValue().nameProperty();
        });
        TableColumn<Fileinfo,String>url= (TableColumn<Fileinfo, String>) this.TableView.getColumns().get(2);
        url.setCellValueFactory(p -> {
            return p.getValue().urlProperty();
        });
        TableColumn<Fileinfo,String>status= (TableColumn<Fileinfo, String>) this.TableView.getColumns().get(3);
        status.setCellValueFactory(p -> {
            return p.getValue().statusProperty();
        });
        TableColumn<Fileinfo,String>action= (TableColumn<Fileinfo, String>) this.TableView.getColumns().get(4);
        action.setCellValueFactory(p -> {
            return p.getValue().actionProperty();
        });
    }

}
