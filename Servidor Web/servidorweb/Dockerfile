FROM node:16.13 as build
WORKDIR /app
COPY package.json /app/

RUN npm install -g ionic
RUN npm install -g axios --save

RUN npm install

COPY ./ /app/

EXPOSE 8100

ENTRYPOINT [ "ionic" ]
CMD [ "serve", "--external" ]

#FROM nginx:alpine
#RUN rm -rf /usr/share/nginx/html/
#COPY --from=build /app/www/ /usr/share/nginx/html/