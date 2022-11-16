package com.bj58;

import com.xxl.tool.emoji.EmojiTool;
import com.xxl.tool.emoji.encode.EmojiEncode;

public class XxlTool {
    public static void main(String[] a){
        String input = "一朵美丽的茉莉🌹\uD83D\uDE00\uD83D\uDE01\uD83D\uDE02\uD83D\uDE03\uD83D\uDE04\uD83D\uDE05\uD83D\uDE06\uD83D\uDE09\uD83D\uDE0A\uD83D\uDE0B\uD83D\uDE0E\uD83D\uDE0D\uD83D\uDE18";
        System.out.println("unicode：" + input);
// 1、alias
        String aliases = EmojiTool.encodeUnicode(input, EmojiEncode.ALIASES);
        System.out.println("\naliases encode: " + aliases);
        System.out.println("aliases decode: " + EmojiTool.decodeToUnicode(aliases, EmojiEncode.ALIASES));
// 2、html decimal
        String decimal = EmojiTool.encodeUnicode(input, EmojiEncode.HTML_DECIMAL);
        System.out.println("\ndecimal encode: " + decimal);
        System.out.println("decimal decode: " + EmojiTool.decodeToUnicode(decimal, EmojiEncode.HTML_DECIMAL));
// 3、html hex decimal
        String hexdecimal = EmojiTool.encodeUnicode(input, EmojiEncode.HTML_HEX_DECIMAL);
        System.out.println("\nhexdecimal encode: " + hexdecimal);
        System.out.println("hexdecimal decode: " + EmojiTool.decodeToUnicode(hexdecimal, EmojiEncode.HTML_HEX_DECIMAL));
    }
}
