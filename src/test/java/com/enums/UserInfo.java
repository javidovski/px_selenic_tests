package com.enums;

/**
 * {@code Enum class} that holds default user's info
 */
public enum UserInfo {
  ADDRESS("1 Main Street"),
  CITY("Boston"),
  COUNTRY("USA"),
  EMAIL_ADDRESS("nowhere@paytronix.com"),
  FIRST_NAME("Test"),
  LAST_NAME("User"),
  PASSWORD("test123"),
  POSTAL_CODE("02302"),
  SALUTATION_DR("Dr."),
  SALUTATION_MR("Mr."),
  SALUTATION_MRS("Mrs."),
  SALUTATION_MS("Ms."),
  SALUTATION_REV("Rev."),
  STATE("Massachusetts"),
  USERNAME("nowhere");
  

  private final String credentials;

  UserInfo(final String credentials) {
    this.credentials = credentials;
  }

  @Override
  public String toString() {
    return credentials;
  }
}
