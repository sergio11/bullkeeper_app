package sanchez.sanchez.sergio.bullkeeper.core.utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple Span Builder
 */
public final class SimpleSpanBuilder {

    private class SpanSection {

        private final String text;
        private final int startIndex;
        private final CharacterStyle[] styles;

        private SpanSection(String text, int startIndex,CharacterStyle... styles){
            this.styles = styles;
            this.text = text;
            this.startIndex = startIndex;
        }

        private void apply(SpannableStringBuilder spanStringBuilder){
            if (spanStringBuilder == null) return;
            for (CharacterStyle style : styles){
                spanStringBuilder.setSpan(style, startIndex, startIndex + text.length(),
                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }
    }

    private List<SpanSection> spanSections;
    private StringBuilder stringBuilder;

    public SimpleSpanBuilder(){
        stringBuilder = new StringBuilder();
        spanSections = new ArrayList<>();
    }

    public SimpleSpanBuilder append(String text,CharacterStyle... styles){
        if (styles != null && styles.length > 0) {
            spanSections.add(new SpanSection(text, stringBuilder.length(),styles));
        }
        stringBuilder.append(text);
        return this;
    }

    public SimpleSpanBuilder appendWithSpace(String text,CharacterStyle... styles){
        return append(text.concat(" "),styles);
    }

    public SimpleSpanBuilder appendWithLineBreak(String text,CharacterStyle... styles){
        return append(text.concat("\n"),styles);
    }

    public SpannableStringBuilder build(){
        SpannableStringBuilder ssb = new SpannableStringBuilder(stringBuilder.toString());
        for (SpanSection section : spanSections){
            section.apply(ssb);
        }
        return ssb;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
