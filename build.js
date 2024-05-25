const fs = require('fs-extra');
const replace = require('replace-in-file');
require('dotenv').config();

// Obtener la URL del backend del archivo .env
const backendURL = process.env.BACKEND_URL;

// Directorios de origen y destino
const sourceDir = './dist';
const targetDir = './deploy';

// Copiar el contenido de la carpeta dist a la carpeta deploy
fs.copy(sourceDir, targetDir, (err) => {
  if (err) {
    console.error('Error al copiar los archivos:', err);
    return;
  }
  console.log('¡Archivos copiados con éxito!');

  // Reemplazar las URLs en los archivos copiados
  const options = {
    files: `${targetDir}/**/*`,
    from: /http:\/\/localhost:8080/g,
    to: backendURL,
  };

  try {
    const changedFiles = replace.sync(options);
    console.log('URLs reemplazadas en los siguientes archivos:', changedFiles.join(', '));
  } catch (error) {
    console.error('Error al reemplazar las URLs:', error);
  }
});
