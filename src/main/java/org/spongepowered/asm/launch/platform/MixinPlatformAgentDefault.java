package org.spongepowered.asm.launch.platform;

import com.sun.tools.doclint.DocLint;
import java.net.URI;
import org.spongepowered.asm.util.Constants;

/* loaded from: target.jar:org/spongepowered/asm/launch/platform/MixinPlatformAgentDefault.class */
public class MixinPlatformAgentDefault extends MixinPlatformAgentAbstract {
    public MixinPlatformAgentDefault(MixinPlatformManager manager, URI uri) {
        super(manager, uri);
    }

    @Override // org.spongepowered.asm.launch.platform.IMixinPlatformAgent
    public void prepare() {
        String compatibilityLevel = this.attributes.get(Constants.ManifestAttributes.COMPATIBILITY);
        if (compatibilityLevel != null) {
            this.manager.setCompatibilityLevel(compatibilityLevel);
        }
        String mixinConfigs = this.attributes.get(Constants.ManifestAttributes.MIXINCONFIGS);
        if (mixinConfigs != null) {
            for (String config : mixinConfigs.split(DocLint.TAGS_SEPARATOR)) {
                this.manager.addConfig(config.trim());
            }
        }
        String tokenProviders = this.attributes.get(Constants.ManifestAttributes.TOKENPROVIDERS);
        if (tokenProviders != null) {
            for (String provider : tokenProviders.split(DocLint.TAGS_SEPARATOR)) {
                this.manager.addTokenProvider(provider.trim());
            }
        }
    }

    @Override // org.spongepowered.asm.launch.platform.IMixinPlatformAgent
    public void initPrimaryContainer() {
    }

    @Override // org.spongepowered.asm.launch.platform.IMixinPlatformAgent
    public void inject() {
    }

    @Override // org.spongepowered.asm.launch.platform.IMixinPlatformAgent
    public String getLaunchTarget() {
        return this.attributes.get(Constants.ManifestAttributes.MAINCLASS);
    }
}
