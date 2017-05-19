package test.org.after90.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zhaogj on 19/05/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebAssetClass {
    private TypeClass url = new TypeClass("keyword");
    private TypeClass ip = new TypeClass("ip");
    private TypeClass banner = new TypeClass("text");
}
