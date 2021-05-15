import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class IncludePlayersController {
    @FXML
    private TableView tableFullPlayers;
    @FXML
    private TableView tablePlayersPitch;
    @FXML
    private TableView tablePlayersBench;

    @FXML
    TableColumn<Player, String> nameAll;
    @FXML
    TableColumn<Player, String> lastNameAll;
    @FXML
    TableColumn<Player, String> positionAll;
    @FXML
    TableColumn<Player, String> statusAll;

    @FXML
    TableColumn<Player, String> namePitch;
    @FXML
    TableColumn<Player, String> lastNamePitch;
    @FXML
    TableColumn<Player, String> positionPitch;
    @FXML
    TableColumn<Player, String> statusPitch;

    @FXML
    TableColumn<Player, String> nameBench;
    @FXML
    TableColumn<Player, String> lastNameBench;
    @FXML
    TableColumn<Player, String> positionBench;
    @FXML
    TableColumn<Player, String> statusBench;

    @FXML
    private Button addPitch;
    @FXML
    private Button removePitch;
    @FXML
    private Button addBench;
    @FXML
    private Button removeBench;

    @FXML private Button save;

    private Match match;
    private PlayerList playerList;

    private ArrayList<Integer> availablePlayers = new ArrayList<Integer>();
    private ArrayList<Integer> pitchPlayers;
    private ArrayList<Integer> benchPlayers;


    public void updateTables() {
        tableFullPlayers.getItems().clear();
        for (int playerId : availablePlayers) {

            tableFullPlayers.getItems().add(playerList.getPlayerByPlayerId(playerId));
        }

        tablePlayersPitch.getItems().clear();
        if (pitchPlayers != null) {
            for (int playerId : pitchPlayers) {
                tablePlayersPitch.getItems().add(playerList.getPlayerByPlayerId(playerId));
            }
        }

        if (benchPlayers != null) {
            tablePlayersBench.getItems().clear();
            for (int playerId : benchPlayers) {
                tablePlayersBench.getItems().add(playerList.getPlayerByPlayerId(playerId));
            }
        }


    }


    public void initialize() {
        if (nameAll != null) {
            nameAll.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
            lastNameAll.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
            statusAll.setCellValueFactory(new PropertyValueFactory<Player, String>("status"));
            positionAll.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
            tableFullPlayers.getItems().clear();
        } else {
            nameAll = new TableColumn<>();
        }

        if (namePitch != null) {
            namePitch.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
            lastNamePitch.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
            statusPitch.setCellValueFactory(new PropertyValueFactory<Player, String>("status"));
            positionPitch.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
            tablePlayersPitch.getItems().clear();
        } else {
            namePitch = new TableColumn<>();
        }

        if (nameBench != null) {
            nameBench.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
            lastNameBench.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
            statusBench.setCellValueFactory(new PropertyValueFactory<Player, String>("status"));
            positionBench.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
            tablePlayersBench.getItems().clear();
        } else {
            nameBench = new TableColumn<>();
        }
        addPitch.disableProperty().bind(Bindings.isEmpty(tableFullPlayers.getSelectionModel().getSelectedItems())); //
        removePitch.disableProperty().bind(Bindings.isEmpty(tablePlayersPitch.getSelectionModel().getSelectedItems())); //
        addBench.disableProperty().bind(Bindings.isEmpty(tableFullPlayers.getSelectionModel().getSelectedItems())); //
        removeBench.disableProperty().bind(Bindings.isEmpty(tablePlayersBench.getSelectionModel().getSelectedItems())); //

    }

    private void initArrayPlayers() {
        if (match.getPlayersPitch() == null) {
            pitchPlayers = new ArrayList<Integer>();
        } else {
            pitchPlayers = match.getPlayersPitch();
        }

        if (match.getPlayersBench() == null) {
            benchPlayers = new ArrayList<Integer>();
        } else {
            benchPlayers = match.getPlayersBench();
        }

        for (int i = 0; i < playerList.getSize(); i++) {
          //  if (!benchPlayers.contains(i) && pitchPlayers.contains(i))
            availablePlayers.add(i); /// ERAGSDFV CORREGIR ESTO URGENTE!! FEO FEO!!
        }



        updateTables();

    }

    public void transferData(Match match, PlayerList playerList) {
        this.match = match;
        this.playerList = playerList;
        initArrayPlayers();
    }

    public void AllMoveToPitch(ActionEvent e){
        Player player = (Player) tableFullPlayers.getSelectionModel().getSelectedItem();
        int indexToClear = availablePlayers.indexOf(player.getPlayerId());
        availablePlayers.remove(indexToClear);
        pitchPlayers.add(player.getPlayerId());
        updateTables();

    }

    public void AllMoveToBench(ActionEvent e){
        Player player = (Player) tableFullPlayers.getSelectionModel().getSelectedItem();
        int indexToClear = availablePlayers.indexOf(player.getPlayerId());
        availablePlayers.remove(indexToClear);
        benchPlayers.add(player.getPlayerId());
        updateTables();

    }

    public void PitchMoveToAll(ActionEvent e){
        Player player = (Player) tablePlayersPitch.getSelectionModel().getSelectedItem();
        int indexToClear = pitchPlayers.indexOf(player.getPlayerId());
        pitchPlayers.remove(indexToClear);
        availablePlayers.add(player.getPlayerId());
        updateTables();

    }

    public void BenchMoveToAll(ActionEvent e){
        Player player = (Player) tablePlayersBench.getSelectionModel().getSelectedItem();
        int indexToClear = benchPlayers.indexOf(player.getPlayerId());
        benchPlayers.remove(indexToClear);
        availablePlayers.add(player.getPlayerId());
        updateTables();

    }

    public void save(ActionEvent e) {
        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }

}
