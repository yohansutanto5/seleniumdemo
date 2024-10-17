FROM selenium/standalone-chrome:latest
# Install Java
USER root
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean

# Ensure Maven is installed (optional)
RUN apt-get install -y maven && \
    apt-get clean

# Set the working directory
WORKDIR /usr/src/app

# Copy your project files into the container
COPY . .

# Build the project (assumes a pom.xml file is present)
# RUN mvn clean
# RUN mvn dependency:resolve

# Command to run tests
CMD ["mvn", "test"]