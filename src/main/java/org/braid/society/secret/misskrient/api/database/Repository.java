package org.braid.society.secret.misskrient.api.database;

public interface Repository<T> {

  void save(T t);

  T get(int id);

  boolean delete(int id);
}
