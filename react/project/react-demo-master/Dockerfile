## ==== CONFIGURE =====
## Use a Node 16 base image
#FROM node:16-alpine
## Set the working directory to /app inside the container
#WORKDIR /app
## Copy app files
#COPY . .
## ==== BUILD =====
## Install dependencies (npm ci makes sure the exact versions in the lockfile gets installed)
#RUN npm ci
## Build the app
#RUN npm run build
## ==== RUN =======
## Set the env to "production"
#ENV NODE_ENV production
## Expose the port on which the app will be running (3000 is the default that `serve` uses)
#EXPOSE 3000
## Start the app
#CMD [ "npx", "serve", "build" ]
##
#FROM node:14 as build-deps
#WORKDIR /usr/src/app
#COPY package.json yarn.lock ./
#RUN yarn
#COPY . ./
#RUN yarn build
#
#FROM nginx:1.12-alpine
#COPY --from=build-deps /usr/src/app/build /usr/share/nginx/html
#EXPOSE 80
#CMD ["nginx", "-g", "daemon off;"]
FROM node:14-alpine

WORKDIR /app

COPY package.json ./

COPY yarn.lock ./

RUN yarn install --frozen-lockfile

COPY . .

EXPOSE 3000

CMD ["npm", "start"]