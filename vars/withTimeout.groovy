def call(timeOutValue, Closure body) {

    try {
        timeout(time: timeOutValue, unit: 'MINUTES') {
            body()
        }
    } catch (err) {
        def user = err.getCauses()[0].getUser()
        if ('SYSTEM' == user.toString()) {
            echo "No input received before timeout"
            currentBuild.result = 'FAILURE'
            error("input step timeout.")

        } else {
            currentBuild.result = 'FAILURE'
            error("Aborted by: [${user}]")
        }

    }

}