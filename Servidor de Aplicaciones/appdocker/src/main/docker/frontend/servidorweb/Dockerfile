FROM node:16.13 as build
WORKDIR /app
COPY package.json /app/

RUN npm install -g ionic
RUN npm install
RUN npm install -g axios --save

COPY ./ /app/

RUN npm run build --prod

FROM nginx:alpine
RUN rm -f /usr/share/nginx/html/
COPY --from=build /app/www/ /usr/share/nginx/html/