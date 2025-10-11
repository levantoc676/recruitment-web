#!/usr/bin/env sh
# wait-for-it.sh — chờ service (MySQL) sẵn sàng trước khi chạy app

host="$1"
port="$2"
shift 2
cmd="$@"

echo "⏳ Waiting for $host:$port..."
until nc -z "$host" "$port"; do
  sleep 2
done

echo "✅ $host:$port is available — executing command..."
exec $cmd
