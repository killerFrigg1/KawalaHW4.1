package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * Created by pwilkin on 30-Nov-17.
 */
public class DaneOsobowe implements HierarchicalController<MainController> {

	public TextField imie;
	public TextField nazwisko;
	public TextField pesel;
	public TextField indeks;
	public TableView<Student> tabelka;
	private MainController parentController;

	public void dodaj(ActionEvent actionEvent) {
		Student st = new Student();
		st.setName(imie.getText());
		st.setSurname(nazwisko.getText());
		st.setPesel(pesel.getText());
		st.setIdx(indeks.getText());
		tabelka.getItems().add(st);
	}

	public void setParentController(MainController parentController) {
		this.parentController = parentController;
		// tabelka.getItems().addAll(parentController.getDataContainer().getStudents());
		tabelka.setEditable(true);
		tabelka.setItems(parentController.getDataContainer().getStudents());
	}

	public void usunZmiany() {
		tabelka.getItems().clear();
		tabelka.getItems().addAll(parentController.getDataContainer().getStudents());
	}

	public MainController getParentController() {
		return parentController;
	}

	public void initialize() {
		for (TableColumn<Student, ?> studentTableColumn : tabelka.getColumns()) {
			if ("imie".equals(studentTableColumn.getId())) {
				TableColumn<Student, String> imieColumn = (TableColumn<Student, String>) studentTableColumn;
				imieColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
				imieColumn.setCellFactory(TextFieldTableCell.forTableColumn());
				imieColumn.setOnEditCommit((val) -> {
					val.getTableView().getItems().get(val.getTablePosition().getRow()).setName(val.getNewValue());
				});
			} else if ("nazwisko".equals(studentTableColumn.getId())) {
				TableColumn<Student, String> nazwiskoColumn = (TableColumn<Student, String>) studentTableColumn;
				nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
				nazwiskoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
				nazwiskoColumn.setOnEditCommit((val) -> {
					val.getTableView().getItems().get(val.getTablePosition().getRow()).setSurname(val.getNewValue());
				});
			} else if ("pesel".equals(studentTableColumn.getId())) {
				TableColumn<Student, String> peselColumn = (TableColumn<Student, String>) studentTableColumn;
				peselColumn.setCellValueFactory(new PropertyValueFactory<>("pesel"));
				peselColumn.setCellFactory(TextFieldTableCell.forTableColumn());
				peselColumn.setOnEditCommit((val) -> {
					val.getTableView().getItems().get(val.getTablePosition().getRow()).setPesel(val.getNewValue());
				});
			} else if ("indeks".equals(studentTableColumn.getId())) {
				TableColumn<Student, String> indexColumn = (TableColumn<Student, String>) studentTableColumn;
				indexColumn.setCellValueFactory(new PropertyValueFactory<>("idx"));
				indexColumn.setCellFactory(TextFieldTableCell.forTableColumn());
				indexColumn.setOnEditCommit((val) -> {
					val.getTableView().getItems().get(val.getTablePosition().getRow()).setIdx(val.getNewValue());
				});
			}
		}

	}

	public void synchronizuj(ActionEvent actionEvent) {
		parentController.getDataContainer().setStudents(tabelka.getItems());
	}
}
