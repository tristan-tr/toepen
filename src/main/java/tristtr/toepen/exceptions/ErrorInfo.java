package tristtr.toepen.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorInfo {
    public final String url;
    public final String ex;
}
