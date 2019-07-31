package practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import practice.controller.model.Validation;
import practice.service.ValidationService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ValidationController {
    @Autowired
    private ValidationService validationService;

    @PostMapping("/validate")
    public ResponseEntity validate(@RequestBody @Valid Validation validation) {
        boolean valid = validationService.validate(validation.getText());

        Validation validatedResult = new Validation();
        validatedResult.setText(validation.getText());
        validatedResult.setValid(valid);
        return createResponseEntity(validatedResult);
    }

    private ResponseEntity createResponseEntity(Object result) {
        Map<String, ? super Object> responseMap = new HashMap<>();
        responseMap.put("data", result);
        responseMap.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(responseMap);
    }
}
