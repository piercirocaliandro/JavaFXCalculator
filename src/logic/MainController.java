package logic;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
	@FXML private Button btnPlus;
	@FXML private Button btnMinus;
	@FXML private Button btnMult;
	@FXML private Button btnDiv;
	@FXML private Button btnResult;
	@FXML private Button btnDel;
	@FXML private HBox hbox1;
	@FXML private HBox hbox2;
	@FXML private HBox hbox3;
	@FXML private HBox hbox4;
	@FXML private TextField tfResult;
	private String n1 = "";
	private String n2 = "";
	private String op;
	
	@FXML
	private void initialize() {
		int i;
		for(i = 1; i < 4; i++) {
			int num = i;
			Button btn = new Button(String.valueOf(i));
			btn.setMinSize(45, 48);
			btn.setOnMouseClicked(e->
				tfResult.setText(tfResult.getText() + num)
			);
			hbox1.getChildren().add(btn);
		}
		for(i = 4; i < 7; i++) {
			int num = i;
			Button btn = new Button(String.valueOf(i));
			btn.setMinSize(45, 48);
			btn.setOnMouseClicked(e->
				tfResult.setText(tfResult.getText() + num)
			);
			hbox2.getChildren().add(btn);
		}
		for(i = 7; i < 10; i++) {
			int num = i;
			Button btn = new Button(String.valueOf(i));
			btn.setMinSize(45, 48);
			btn.setOnMouseClicked(e->
				tfResult.setText(tfResult.getText() + num)
			);
			hbox3.getChildren().add(btn);
		}
		Button btn = new Button(String.valueOf(0));
		btn.setMinSize(45, 48);
		btn.setOnMouseClicked(e->
			tfResult.setText(tfResult.getText() + 0)
		);
		hbox4.getChildren().add(btn);
	}
	
	public void setNum(int who) {
		String num = this.tfResult.getText();
		if(!num.equalsIgnoreCase("")) {
			if(who == 1) {
				this.n1 = num;
			}
			else {
				this.n2 = num;
			}
		}
		this.tfResult.setText("");
	}
	
	public void setOp(MouseEvent e) {
		if(e.getSource() == this.btnPlus) {
			this.op = "+";
		}
		else if(e.getSource() == this.btnMinus) {
			this.op = "-";
		}
		else if(e.getSource() == this.btnMult) {
			this.op = "*";
		}
		else {
			this.op = "/";
		}
		setNum(1);
	}
	
	public void delNum(MouseEvent e) {
		if(e.getSource() == this.btnDel) {
			String currNum = this.tfResult.getText();
			if(!currNum.equalsIgnoreCase("")) {
				currNum = String.valueOf(currNum.subSequence(0, currNum.length()-1));
				this.tfResult.setText(currNum);
			}
		}
	}
	
	public void resultFromOp(MouseEvent e) {
		if(e.getSource() == this.btnResult) {
			setNum(2);
			if(this.n1.equalsIgnoreCase("") || this.n2.equalsIgnoreCase("")) {
				this.tfResult.setText(String.valueOf(""));
				this.n1 = "";
				this.n2 = "";
				return;
			}
			float res = 0;
			switch(op) {
				case("+"):
					res = Float.parseFloat(this.n1) + Float.parseFloat(this.n2);
					break;
				case("-"):
					res = Float.parseFloat(this.n1) - Float.parseFloat(this.n2);
					break;
				case("*"):
					res = Float.parseFloat(this.n1) * Float.parseFloat(this.n2);
					break;
				case("/"):
					if(Integer.valueOf(this.n2) != 0) {
						res = Float.parseFloat(this.n1) / Float.parseFloat(this.n2);
					}
					else {
						displayErrorPopup();
						this.tfResult.setText(String.valueOf(""));
						return;
					}
					break;
				default : 
				}
			this.tfResult.setText(String.valueOf(res));
			this.n1 = "";
			this.n2 = "";
		}
	}
	
	public void displayErrorPopup() {
		Stage window = new Stage();
		window.setWidth(500);
		window.setHeight(300);
		window.initModality(Modality.APPLICATION_MODAL); //this avoid user to interact with other users
		Text label = new Text();
		label.setText("Error : division by 0");
		Button closeButton = new Button("close");
		closeButton.setOnAction(e->window.close());
		VBox layout = new VBox(20);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}
