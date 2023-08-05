package wanted.common;

import org.springframework.stereotype.Component;
import wanted.security.jwt.port.ClockHolder;

import java.util.Date;

@Component
public class SystemClockHolder implements ClockHolder {
    @Override
    public Date now() {
        return new Date();
    }
}
