# Stage 1
FROM node:10-alpine as build-step
RUN mkdir -p /app
WORKDIR /app
COPY . /app
RUN npm install
RUN npm run build -- --prod

# Stage 2
FROM nginx:1.17.1-alpine
COPY --from=build-step /app/dist/cinema-frontend/ /usr/share/nginx/html/
COPY ./nginx_custom.conf /etc/nginx/conf.d/default.conf
