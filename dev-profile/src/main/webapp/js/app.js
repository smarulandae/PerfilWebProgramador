document.addEventListener('DOMContentLoaded', () => {
  const params = new URLSearchParams(window.location.search);
  if (params.get('saved') === '1') {
    console.log('Cambios guardados.');
  }
});
