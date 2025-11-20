package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.Kontak;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class KontakController implements Initializable {

    @FXML private TableView<Kontak> tableKontak;
    @FXML private TableColumn<Kontak, Integer> colId;
    @FXML private TableColumn<Kontak, String> colNama;
    @FXML private TableColumn<Kontak, String> colTelepon;
    @FXML private TableColumn<Kontak, String> colEmail;
    @FXML private TableColumn<Kontak, String> colAlamat;

    @FXML private TextField txtNama;
    @FXML private TextField txtTelepon;
    @FXML private TextField txtEmail;
    @FXML private TextField txtAlamat;

    @FXML private Button btnTambah;
    @FXML private Button btnEdit;
    @FXML private Button btnHapus;

    private final ObservableList<Kontak> kontakList = FXCollections.observableArrayList();
    private int nextId = 1;
    private Kontak selectedKontak = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colTelepon.setCellValueFactory(new PropertyValueFactory<>("telepon"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));

        tableKontak.setItems(kontakList);

        tableKontak.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            selectedKontak = newVal;
            if (newVal != null) {
                txtNama.setText(newVal.getNama());
                txtTelepon.setText(newVal.getTelepon());
                txtEmail.setText(newVal.getEmail());
                txtAlamat.setText(newVal.getAlamat());
            }
        });
    }

    @FXML
    private void onTambah() {
        if (txtNama.getText().isEmpty() || txtTelepon.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Nama dan Telepon wajib diisi!").showAndWait();
            return;
        }
        Kontak k = new Kontak(nextId++, txtNama.getText(), txtTelepon.getText(), txtEmail.getText(), txtAlamat.getText());
        kontakList.add(k);
        clearInput();
    }

    @FXML
    private void onEdit() {
        if (selectedKontak == null) {
            new Alert(Alert.AlertType.WARNING, "Pilih kontak yang akan diedit.").showAndWait();
            return;
        }

        selectedKontak.setNama(txtNama.getText());
        selectedKontak.setTelepon(txtTelepon.getText());
        selectedKontak.setEmail(txtEmail.getText());
        selectedKontak.setAlamat(txtAlamat.getText());
        tableKontak.refresh();
        clearInput();
    }

    @FXML
    private void onHapus() {
        if (selectedKontak == null) {
            new Alert(Alert.AlertType.WARNING, "Pilih kontak yang akan dihapus.").showAndWait();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Yakin ingin menghapus " + selectedKontak.getNama() + "?",
                ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> res = confirm.showAndWait();
        if (res.isPresent() && res.get() == ButtonType.OK) {
            kontakList.remove(selectedKontak);
            clearInput();
        }
    }

    private void clearInput() {
        txtNama.clear();
        txtTelepon.clear();
        txtEmail.clear();
        txtAlamat.clear();
        tableKontak.getSelectionModel().clearSelection();
        selectedKontak = null;
    }
}
