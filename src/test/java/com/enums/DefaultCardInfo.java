package com.enums;

/**
 * <p>
 *  {@code Enum class} that stores <br>
 * </p>
 * <ul>
 *  <li>
 *    card number <br>
 *  </li>
 *  <li>
 *    registration code
 *  </li>
 * </ul>
 */
public enum DefaultCardInfo {
  CARD_NUM("1010101090000303"),
  REGISTRATION_CODE("123456");
  

  private final String str;

  DefaultCardInfo(final String str) {
    this.str = str;
  }

  @Override
  public String toString() {
    return str;
  }
}
