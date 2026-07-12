package com.sun.xml.internal.rngom.digested;

/* loaded from: target.jar:com/sun/xml/internal/rngom/digested/DPatternVisitor.class */
public interface DPatternVisitor<V> {
    V onAttribute(DAttributePattern dAttributePattern);

    V onChoice(DChoicePattern dChoicePattern);

    V onData(DDataPattern dDataPattern);

    V onElement(DElementPattern dElementPattern);

    V onEmpty(DEmptyPattern dEmptyPattern);

    V onGrammar(DGrammarPattern dGrammarPattern);

    V onGroup(DGroupPattern dGroupPattern);

    V onInterleave(DInterleavePattern dInterleavePattern);

    V onList(DListPattern dListPattern);

    V onMixed(DMixedPattern dMixedPattern);

    V onNotAllowed(DNotAllowedPattern dNotAllowedPattern);

    V onOneOrMore(DOneOrMorePattern dOneOrMorePattern);

    V onOptional(DOptionalPattern dOptionalPattern);

    V onRef(DRefPattern dRefPattern);

    V onText(DTextPattern dTextPattern);

    V onValue(DValuePattern dValuePattern);

    V onZeroOrMore(DZeroOrMorePattern dZeroOrMorePattern);
}
