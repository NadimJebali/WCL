# Image node de dernier version
FROM node:20

# Définir le répertoire de travail
WORKDIR /usr/src/app

# Copier les fichiers de dépendances
COPY package.json package-lock.json* ./

# Installer les dépendances
RUN npm install

# Installer Angular CLI globalement
RUN npm install -g @angular/cli

# Copier tous les fichiers du projet
COPY . .

# Exposer le port 4200 (port par défaut d'Angular)
EXPOSE 4200

# Commande pour démarrer le serveur de développement
CMD ["ng", "serve","--host", "0.0.0.0", "--disable-host-check"]