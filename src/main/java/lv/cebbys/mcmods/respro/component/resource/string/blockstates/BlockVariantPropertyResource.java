package lv.cebbys.mcmods.respro.component.resource.string.blockstates;

import lombok.Getter;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.BlockVariantPropertyResourceInitializer;
import lv.cebbys.mcmods.respro.component.resource.core.AbstractStringResource;
import lv.cebbys.mcmods.respro.exception.ResourceValidationException;
import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

@Getter
public class BlockVariantPropertyResource extends AbstractStringResource implements BlockVariantPropertyResourceInitializer {
    private final Set<String> properties = new HashSet<>();

    @Override
    public @NotNull BlockVariantPropertyResourceInitializer setNormal() {
        properties.add("normal");
        return this;
    }

    @Override
    public @NotNull BlockVariantPropertyResourceInitializer setProperty(@NotNull String id, @NotNull String value) {
        properties.add(id + "=" + value);
        return this;
    }

    @Override
    public boolean belongsTo(@NotNull PackType type) {
        return PackType.CLIENT_RESOURCES.equals(type);
    }

    @Override
    public @NotNull("BlockVariantPropertyResource content is null") String getAsString() {
        StringBuilder builder = new StringBuilder();
        properties.forEach(property -> {
            if (!builder.isEmpty()) builder.append(",");
            builder.append(property);
        });
        return builder.toString();
    }

    @SuppressWarnings("all")
    @Override
    public void validate() throws ResourceValidationException {
        try {
            getAsString();
        } catch (Exception e) {
            throw new ResourceValidationException("Failed to validate BlockVariantPropertyResource", e);
        }
    }
}
