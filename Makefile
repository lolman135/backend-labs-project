USR_SERV_BUILD := UserService/build/libs
CUR_SERV_BUILD := CurrencyService/build/libs
EXP_SERV_BUILD := ExpenseService/build/libs
AUTH_SERV_BUILD := AuthService/build/libs
GATEWAY_SERV_BUILD := ApiGatewayService/build/libs
HEALTH_SERV_BUILD := HealthcheckService/build/libs

BUILDS := $(USR_SERV_BUILD) $(CUR_SERV_BUILD) $(EXP_SERV_BUILD) $(AUTH_SERV_BUILD) $(GATEWAY_SERV_BUILD) $(HEALTH_SERV_BUILD)

JAR_PATTERN := *SNAPSHOT.jar
ZIP_DIR := final_build
ZIP_FILE := deploy_build.zip

all: gradle_build copy zip

gradle_build:
	./gradlew clean build -x test

copy:
	mkdir -p $(ZIP_DIR)
	@$(foreach build_dir,$(BUILDS),cp $(build_dir)/$(JAR_PATTERN) $(ZIP_DIR);)

zip:
	zip $(ZIP_FILE) $(ZIP_DIR)/*
	rm -rf $(ZIP_DIR)

clean:
	./gradlew clean
	rm -rf $(ZIP_DIR)
	rm $(ZIP_FILE)