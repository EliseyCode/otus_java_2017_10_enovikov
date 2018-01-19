<b>Домашнее задание № 10:
Hibernate ORM</b>

На основе предыдущего ДЗ (myORM):
1. Оформить решение в виде DBService (interface DBService, class DBServiceImpl, UsersDAO, UsersDataSet, Executor)
2. Не меняя интерфейс DBSerivice сделать DBServiceHibernateImpl на Hibernate.
3. Добавить в UsersDataSet поля:
адресс (OneToOne) 
class AddressDataSet{
private String street;
}