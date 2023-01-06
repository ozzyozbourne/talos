FROM gradle:jdk17-focal

WORKDIR /rsap

COPY . .

RUN gradle testClasses

CMD ["gradle", "build"]
