package com.ferusgrim.residence.util;

import com.google.common.collect.Lists;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ResOut {

    private static final String OUTPUT_IDENTIFIER = "[Residence] ";
    private static final Text PREPEND_FINE = Text.builder(OUTPUT_IDENTIFIER)
            .color(TextColors.GREEN)
            .style(TextStyles.BOLD).build();
    private static final Text PREPEND_WARN = Text.builder(OUTPUT_IDENTIFIER)
            .color(TextColors.YELLOW)
            .style(TextStyles.BOLD).build();
    private static final Text PREPEND_ERROR = Text.builder(OUTPUT_IDENTIFIER)
            .color(TextColors.RED)
            .style(TextStyles.ITALIC, TextStyles.BOLD).build();

    @Nonnull private static Text build(@Nonnull final Text... texts) {
        if (texts.length == 1) {
            return texts[0];
        }

        final Text.Builder builder = Text.builder();

        for (final Text text : texts) {
            builder.append(text);
        }

        return builder.build();
    }

    @Nonnull private static Text convertMessage(@Nonnull final String message) {
        return Text.of(TextColors.GRAY, TextStyles.ITALIC, message);
    }

    @Nullable private static Text convertObject(@Nonnull final Object message) {
        if (message instanceof Text) {
            return (Text) message;
        }

        if (message instanceof String) {
            return convertMessage((String) message);
        }

        // Currently only support Text/String objects.
        return null;
    }

    @Nonnull private static List<Text> compileParts(Object... parts) {
        final List<Text> texts = Lists.newArrayList();

        for (final Object part : parts) {
            final Text text = convertObject(part);

            if (text == null) continue;

            texts.add(text);
        }

        return texts;
    }

    @Nonnull public static Text fine(@Nonnull final String message) {
        return build(PREPEND_FINE, convertMessage(message));
    }

    @Nonnull public static Text warn(@Nonnull final String message) {
        return build(PREPEND_WARN, convertMessage(message));
    }

    @Nonnull public static Text error(@Nonnull final String message) {
        return build(PREPEND_ERROR, convertMessage(message));
    }

    @Nonnull public static Text fine(@Nonnull final Object... parts) {
        return build(PREPEND_FINE, Text.join(compileParts(parts)));
    }

    @Nonnull public static Text warn(@Nonnull final Object... parts) {
        return build(PREPEND_WARN, Text.join(compileParts(parts)));
    }

    @Nonnull public static Text error(@Nonnull final Object... parts) {
        return build(PREPEND_ERROR, Text.join(compileParts(parts)));
    }
}
