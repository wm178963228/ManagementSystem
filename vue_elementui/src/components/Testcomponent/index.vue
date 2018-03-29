<template>
  <li>{{ test.text }}</li>
</template>

<script>
  export default {
    props: {
      test: {
        text: String,
        required: true
      }
    }
  }
</script>

<style>
</style>
