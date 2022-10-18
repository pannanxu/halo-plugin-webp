package run.halo.starter;

import org.pf4j.PluginWrapper;
import org.springframework.stereotype.Component;
import run.halo.app.extension.SchemeManager;
import run.halo.app.plugin.BasePlugin;

/**
 * @author guqing
 * @since 2.0.0
 */
@Component
public class StarterPlugin extends BasePlugin {
    private final SchemeManager schemeManager;

    public StarterPlugin(PluginWrapper wrapper, SchemeManager schemeManager) {
        super(wrapper);
        this.schemeManager = schemeManager;
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }
}