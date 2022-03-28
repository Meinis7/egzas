package com.example.filmai.controller;

import com.example.filmai.MainApplication;
import com.example.filmai.model.*;
import com.example.filmai.utills.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private TextField idSearch;
    @FXML
    private TableView filmaiTable;
    @FXML
    private TableColumn idTable;
    @FXML
    private TableColumn filmasTable;
    @FXML
    private TableColumn IMDBTable;
    @FXML
    private TableColumn kategorijaTable;
    @FXML
    private TableColumn aprasymasTable;
    @FXML
    private TextField filmoPav;
    @FXML
    private ChoiceBox choiseBoxYear;
    @FXML
    private Label tableStatus;
    @FXML
    private Label grupeLabel;
    @FXML
    private Label vardasLabel;
    @FXML
    private TextField aprasymasField;
    @FXML
    private TextField imdbField;
    @FXML
    private TextField filmasField;
    @FXML
    private TextField kategorijaField;

    ObservableList<Movie> list = FXCollections.observableArrayList();

    @FXML
    public void searchButtonClick(){

        list.clear();
        String titlefield=filmoPav.getText();

        List<Movie> movieList=MovieDao.searchByName(titlefield,grupeLabel.getText());
        for(Movie movie:movieList){
            list.add(new Movie(movie.getId(),movie.getTitle(),movie.getIMDB(),movie.getKategorija(),movie.getAprasymas(),movie.getUser_id()));

            idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
            filmasTable.setCellValueFactory(new PropertyValueFactory<>("title"));
            IMDBTable.setCellValueFactory(new PropertyValueFactory<>("IMDB"));
            kategorijaTable.setCellValueFactory(new PropertyValueFactory<>("kategorija"));
            aprasymasTable.setCellValueFactory(new PropertyValueFactory<>("aprasymas"));
            filmaiTable.setItems(list);
        }
        if(movieList.isEmpty()){
            tableStatus.setText("Nepavyko atlikti paieskos pagal filmu pavadinimus");
        }else {
            tableStatus.setText("Pavyko atlikti paieska pagal filmu pavadinimus");
        }
    }

    @FXML
    public void onKategorijaCreate(){
        String kat=kategorijaField.getText();
        if(grupeLabel.getText().equals("Autentifikuotas")){
            if (!Validation.isValidKategorija(kat)) {
                tableStatus.setText("Blogas pavadinimas kategorijai");
            } else {
                Kategorija kategorija = new Kategorija(kat);
                KategorijaDAO.createKategorija(kategorija);
                tableStatus.setText("Pavyko sukurti kategorija");
            }
        }else {
            tableStatus.setText("Kurti kategorija gali tik autentifikuotas vartotojas");
        }
    }
    @FXML
    public void onKategorijaDelete(){
        String idfield=kategorijaField.getText();
        if(grupeLabel.getText().equals("Autentifikuotas")) {
            if (!Validation.isValidIMDB(idfield)) {
                tableStatus.setText("Neteisingas id");
            } else {
                KategorijaDAO.deleteByName(kategorijaField.getText());
                tableStatus.setText("Pavyko pasalinti irasa");
            }
        }else {
            tableStatus.setText("Trinti kategorija gali tik autentifikuotas vartotojas");
        }
    }
    @FXML
    public void onKategorijaUpdate() {
        String kate=kategorijaField.getText();
        String id=idSearch.getText();

        if (grupeLabel.getText().equals("Autentifikuotas")) {
            if(!Validation.isValidKategorija(kate)) {
                tableStatus.setText("Neteisinga kategorija");
            }else {
                int id2=Integer.parseInt(id);
                Kategorija kategorija = new Kategorija(id2,kate);
               KategorijaDAO.updateKategorija(kategorija);
                tableStatus.setText("Pavyko redaguoti");
            }
            }else {
            tableStatus.setText("Trinti kategorija gali tik autentifikuotas vartotojas");
        }

        }

    @FXML
    public void createOnClick(){
        String aprasymas=aprasymasField.getText();
        String imdb=imdbField.getText();
        String filmas=filmasField.getText();

        if(grupeLabel.getText().equals("Autentifikuotas")) {
            String kategorija="";
            if(!choiseBoxYear.getSelectionModel().isEmpty()){
                kategorija=choiseBoxYear.getSelectionModel().getSelectedItem().toString();
            }
            if(!Validation.isValidTitle(filmas)){
                tableStatus.setText("Neteisingas filmo pavadinimas");
            }else if(!Validation.isValidIMDB(imdb)){
                tableStatus.setText("Neteisingas IMDB");
            }
            else if(!Validation.isValidAprasymas(aprasymas)){
                tableStatus.setText("Neteisingas aprasymas");
            }else {

                float imdb2=Float.parseFloat(imdbField.getText());
                int userID=UserDAO.searchByUsernameReturnId(vardasLabel.getText());
                Movie movies=new Movie(filmas,imdb2,kategorija,aprasymas,userID);
                MovieDao.create(movies,grupeLabel.getText());
                tableStatus.setText("Pavyko sukurti filmo irasa");
            }

        }else {
            tableStatus.setText("Kurti irasa gali tik autentifikuotas vartotojas");
        }

    }

    @FXML
    public void onUpdateClick(){
        String titlefield=filmoPav.getText();
        String idfield=idSearch.getText();
        String imdb=imdbField.getText();
        String aprasymas=aprasymasField.getText();

        if(grupeLabel.getText().equals("Autentifikuotas")) {

            String kat = "";
            if (!choiseBoxYear.getSelectionModel().isEmpty()) {
                kat = choiseBoxYear.getSelectionModel().getSelectedItem().toString();
            }

            if (!Validation.isValidIMDB(idfield)) {
                tableStatus.setText("Neteisingas id");
            } else if (!Validation.isValidTitle(titlefield)) {
                tableStatus.setText("Neteisingas filmo pavadinimas");
            } else if (!Validation.isValidIMDB(imdb)) {
                tableStatus.setText("Neteisinga filmo trukme");
            }else if(!Validation.isValidAprasymas(aprasymas)){
                tableStatus.setText("Neteisingas aprašymas");
            }
            else {
                int id = Integer.parseInt(idSearch.getText());
                float imdb2=Float.parseFloat(imdbField.getText());
                int userID=UserDAO.searchByUsernameReturnId(vardasLabel.getText());
                Movie movies=new Movie(titlefield,imdb2,kat,aprasymas,userID);
                MovieDao.update(movies);
                tableStatus.setText("Pavyko redaguoti");

            }
        }
        else {
            tableStatus.setText("Redaguoti gali tik autentifikuotas vartotojas");
        }
    }

    @FXML
    public void onDeleteClick(){
        String idfield=idSearch.getText();
        if(grupeLabel.getText().equals("Autentifikuotas")) {
            if (!Validation.isValidIMDB(idfield)) {
                tableStatus.setText("Neteisingas id");
            } else {
                int id = Integer.parseInt(idSearch.getText());
                MovieDao.deleteById(id);
                tableStatus.setText("Pavyko pasalinti irasa");
            }
        }else {
            tableStatus.setText("Trinti irasa gali tik autentifikuotas vartotojas");
        }
    }
    @FXML
    public void onLogOutClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("login-view.fxml"));
        Stage registerStage = new Stage();
        registerStage.setScene(new Scene(root, 600, 400));
        registerStage.setTitle("Prisijungimo langas");
        registerStage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiseBoxYear.getItems().addAll("Veiksmo");
        choiseBoxYear.getItems().addAll("Komedija");
        choiseBoxYear.getItems().addAll("Siaubo");


        choiseBoxYear.getSelectionModel().selectFirst();

        //parodomas prissjunges vartotojas
        String username=UserSingleton.getInstance().getUsername();
        vardasLabel.setText(username);
        //pardooma prisijungusio vartotojo role

        String role=UserDAO.searchByUsername(username);
        grupeLabel.setText(role);
    }
}
