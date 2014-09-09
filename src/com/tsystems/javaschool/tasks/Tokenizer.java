package com.tsystems.javaschool.tasks;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cuts incoming string into data tokens based on given grammar, stored in TokenInfo in form of compiled regexes.
 *
 * Created by sevasan on 05.09.14.
 */
public class Tokenizer {
    /**
     * TokenInfo contains information about a single grammar token
     */
    private class TokenInfo {
        public final Pattern regex;
        public final int tokenId;

        public TokenInfo(Pattern regex, int tokenId) {
            super();

            this.regex = regex;
            this.tokenId = tokenId;
        }
    }

    private LinkedList<TokenInfo> tokenInfos;
    private LinkedList<Token> tokens;

    public Tokenizer() {
        tokenInfos = new LinkedList<TokenInfo>();
        tokens = new LinkedList<Token>();
    }

    public void add(String regex, int tokenId) {
        tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), tokenId));
    }

    /**
     * Breaks string into separate tokens according to specified grammar.
     *
     * @param str
     *          String to tokenize
     */

    public void tokenize(String str) {
        String s = str.trim();
        tokens.clear();

        while (!s.equals("")) {
            boolean match = false;

            for (TokenInfo info : tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;

                    String tok = m.group().trim();
                    tokens.add(new Token(info.tokenId, tok));

                    s = m.replaceFirst("").trim();
                    break;
                }
            }

            if (!match) {
                throw new ParserException("Unexpected symbol found!");
            }
        }
    }

    public LinkedList<Token> getTokens() {
        return tokens;
    }
}
