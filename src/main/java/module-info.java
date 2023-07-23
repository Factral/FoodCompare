module com.example.foodcompare_vm {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.foodcompare_vm to javafx.fxml;
    exports com.example.foodcompare_vm;
}