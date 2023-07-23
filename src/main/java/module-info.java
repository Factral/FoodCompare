module com.example.foodcompare_vm {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;

                            
    opens com.example.foodcompare_vm to javafx.fxml;
    exports com.example.foodcompare_vm;
}