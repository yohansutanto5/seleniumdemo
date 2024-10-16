# Use a base image with Java 17 installed
FROM eclipse-temurin:17-jdk

# Install wget, unzip, and other necessary tools
RUN apt-get update && apt-get install -y wget unzip curl gnupg

# Install Chrome
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list \
    && apt-get update && apt-get install -y google-chrome-stable

# Install Maven
RUN apt-get install -y maven

# Set up working directory
WORKDIR /usr/src/app

# Copy the project files
COPY . .
# Run Maven test
CMD ["mvn", "test"]