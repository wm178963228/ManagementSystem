<template>
  <li>
    {{ title.text }}
    <button @click="$emit('remove')">X</button>
  </li>
</template>

<script>
  export default {
    props: {
      title: {
        text: String,
        required: true
      }
    }
  }
</script>

<style>
</style>
