package agents;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductAgent {
    @Getter @Setter private int id;
    @Getter @Setter private String name;
    @Getter @Setter private int quantity;
}
