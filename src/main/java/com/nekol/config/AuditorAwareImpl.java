package com.nekol.config;

import com.nekol.utils.UserUtil;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(UserUtil.getUserCurrenUtil().getUsername());
    }
}
