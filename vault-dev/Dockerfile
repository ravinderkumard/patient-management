# Use official Vault base image
FROM hashicorp/vault:latest

# Set environment variables (dev mode)
ENV VAULT_DEV_ROOT_TOKEN_ID=myroot
ENV VAULT_DEV_LISTEN_ADDRESS=0.0.0.0:8200

# Expose Vault port
EXPOSE 8200

# Run Vault in dev mode
ENTRYPOINT ["vault"]
CMD ["server", "-dev", "-dev-root-token-id=myroot", "-dev-listen-address=0.0.0.0:8200"]
