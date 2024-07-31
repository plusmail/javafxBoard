package kroryi.board.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kroryi.board.dto.User;
import kroryi.board.service.UserService;
import kroryi.board.service.UserServiceImpl;
import kroryi.board.util.SceneUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    public TextField tfId;
    public TextField tfUsername;
    public PasswordField tfPwRe;
    public PasswordField tfPw;

    Stage stage;
    Scene scene;
    Parent root;

    private UserService userService = new UserServiceImpl();


    public void register(ActionEvent event) throws IOException {
        String userid = tfId.getText();
        String username = tfUsername.getText();
        String password = tfPw.getText();
        String password_re = tfPwRe.getText();
        if(userid == null || userid.trim().isEmpty()){
            showAlert("아이디를 입력하시오.");
            return;
        }

        if(username == null || username.trim().isEmpty()){
            showAlert("사용자명을 입력하시오.");
            return;
        }
        if(password == null || password.trim().isEmpty()){
            showAlert("비밀번호를 입력하시오.");
        }

        if(password_re == null || password_re.trim().isEmpty()){
            showAlert("(재)비밀번호를 입력하시오.");
            return;
        }

        assert password != null;
        if(!password.trim().equals(password_re.trim())){
            showAlert("비번호가 일치 하지 않습니다.");
            return;
        }else{
            User user = new User(tfId.getText(), tfUsername.getText(), tfPw.getText());
            int result = userService.insert(user);
            if(result > 0){
                showAlert("회원가입 성공");
                SceneUtil.getInstance().switchScene(event,UI.LOGIN.getPath());
            }else{
                showAlert("회원가입에 문제가 발생 했습니다.");
            }
        }


    }

    public void gotoLogin(ActionEvent event){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("입력 오류");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
