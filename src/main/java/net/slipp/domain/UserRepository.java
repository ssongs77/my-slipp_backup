package net.slipp.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId); //findBy + '조회하고 싶은 속성이름(첫글자 대문자)' (속성) 값을 주면 조회 가능
}
