package africa.semicolon.employeemanagementsystems.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateResponse {
    private String message;
    @Override
    public  String toString(){
        return message;
    }
}
